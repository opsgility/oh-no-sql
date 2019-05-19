param($user="", $password="")
$ErrorActionPreference = 'SilentlyContinue'


#$sourceFileUrl="https://openhackguides.blob.core.windows.net/no-sql-artifacts/published-app-java.zip"
$dbsource="https://openhackguides.blob.core.windows.net/no-sql-artifacts/OpenHack.bak"
#$destinationFolder="C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\ROOT"
$databaseName="OpenHack.bak"

# Install JDK 1.8
$jdkUrl = "https://openhackguides.blob.core.windows.net/no-sql-artifacts/jdk-8u211-windows-x64.exe"
$jreUrl = "https://openhackguides.blob.core.windows.net/no-sql-artifacts/jre-8u211-windows-x64.exe"
#$tomcatUrl = "https://openhackguides.blob.core.windows.net/no-sql-artifacts/apache-tomcat-8.5.40.exe"

$javaDownloadPath = "C:\JavaInstall"
if((Test-Path $javaDownloadPath) -eq $false)
{
    New-Item -Path $javaDownloadPath -ItemType directory
}


$jdkInstall = Join-Path $javaDownloadPath "jdk-8u211-windows-x64.exe"
$jreInstall = Join-Path $javaDownloadPath "jre-8u211-windows-x64.exe"
$apacheInstall = Join-Path $javaDownloadPath "apache-tomcat-8.5.40.exe"

(New-Object Net.WebClient).DownloadFile($jdkUrl,$jdkInstall);
(New-Object Net.WebClient).DownloadFile($jreUrl,$jreInstall);
(New-Object Net.WebClient).DownloadFile($tomcatUrl,$apacheInstall);


& $jdkInstall /s

Start-Sleep -Seconds 300

# Set ENV Variable JAVA-HOME
setx JAVA_HOME "C:\Program Files\Java\jdk1.8.0_211"


& $apacheInstall /S

# Clear the default files 
Remove-Item –path $destinationFolder -Recurse -Force

New-Item -Path $destinationFolder -ItemType directory

# Download and deploy the sample app

if([string]::IsNullOrEmpty($sourceFileUrl) -eq $false -and [string]::IsNullOrEmpty($destinationFolder) -eq $false)
{
    if((Test-Path $destinationFolder) -eq $false)
    {
        New-Item -Path $destinationFolder -ItemType directory
    }
    $splitpath = $sourceFileUrl.Split("/")
    $fileName = $splitpath[$splitpath.Length-1]
    $destinationPath = Join-Path $destinationFolder $fileName

    (New-Object Net.WebClient).DownloadFile($sourceFileUrl,$destinationPath);

    (new-object -com shell.application).namespace($destinationFolder).CopyHere((new-object -com shell.application).namespace($destinationPath).Items(),16)
}


Net Start Tomcat8

Stop-Process -Name Explorer
Write-Host "IE Enhanced Security Configuration (ESC) has been disabled." -ForegroundColor Green


# Install Chrome
$Path = $env:TEMP; 
$Installer = "chrome_installer.exe"
Invoke-WebRequest "http://dl.google.com/chrome/install/375.126/chrome_installer.exe" -OutFile $Path\$Installer
Start-Process -FilePath $Path\$Installer -Args "/silent /install" -Verb RunAs -Wait
Remove-Item $Path\$Installer


# Deploy the DB
$logs    = "C:\Logs"
$data    = "C:\Data"
$backups = "C:\Backup" 
$script  = "C:\Script" 

[system.io.directory]::CreateDirectory($logs)
[system.io.directory]::CreateDirectory($data)
[system.io.directory]::CreateDirectory($backups)
[system.io.directory]::CreateDirectory($script)
[system.io.directory]::CreateDirectory("C:\SQLDATA")


$destinationPath = "$script\configure-sql.ps1"
# Download config script
(New-Object Net.WebClient).DownloadFile("https://raw.githubusercontent.com/opsgility/oh-no-sql/master/configure-sql.ps1",$destinationPath);

# Get the Adventure works database backup 
$dbdestination = "C:\SQLDATA\$databaseName"
Invoke-WebRequest $dbsource -OutFile $dbdestination

$password =  ConvertTo-SecureString "$password" -AsPlainText -Force
$credential = New-Object System.Management.Automation.PSCredential("$env:COMPUTERNAME\$user", $password)

Enable-PSRemoting -Force
Set-NetFirewallRule -Name "WINRM-HTTP-In-TCP-PUBLIC" -RemoteAddress Any
Invoke-Command -FilePath $destinationPath -Credential $credential -ComputerName $env:COMPUTERNAME -ArgumentList "Password", $password
Disable-PSRemoting -Force

New-NetFirewallRule -DisplayName "SQL Server" -Direction Inbound -Protocol TCP -LocalPort 1433 -Action allow 
