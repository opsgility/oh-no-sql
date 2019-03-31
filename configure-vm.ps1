param($user="demouser", $password="demo@pass123", $sourceFileUrl="https://openhackguides.blob.core.windows.net/no-sql-artifacts/published-app.zip", $destinationFolder="C:\inetpub\wwwroot",$dbsource="https://openhackguides.blob.core.windows.net/no-sql-artifacts/OpenHack.bak", $databaseName="OpenHack.bak")
$ErrorActionPreference = 'SilentlyContinue'

Install-WindowsFeature -Name Web-Server -IncludeAllSubFeature

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
