param($user="", $password="")
$ErrorActionPreference = 'SilentlyContinue'

$sourceFileUrl="https://openhackguides.blob.core.windows.net/no-sql-artifacts/published-app.zip"
$dbsource="https://openhackguides.blob.core.windows.net/no-sql-artifacts/OpenHack.bak"
$destinationFolder="C:\inetpub\wwwroot"
$databaseName="OpenHack.bak"

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


#$destinationPath = "$script\configure-sql.ps1"
# Download config script
#(New-Object Net.WebClient).DownloadFile("https://raw.githubusercontent.com/opsgility/oh-no-sql/master/configure-sql.ps1",$destinationPath);

# Get the Adventure works database backup 
$dbdestination = "C:\SQLDATA\$databaseName"
Invoke-WebRequest $dbsource -OutFile $dbdestination

$password =  ConvertTo-SecureString "$password" -AsPlainText -Force
$credential = New-Object System.Management.Automation.PSCredential("$env:COMPUTERNAME\$user", $password)


$configureSQLBlock =
{

    $dbdestination = "C:\SQLDATA\OpenHack.bak"
    # Setup the data, backup and log directories as well as mixed mode authentication
    Import-Module "sqlps" -DisableNameChecking
    [System.Reflection.Assembly]::LoadWithPartialName("Microsoft.SqlServer.Smo")
    $sqlesq = new-object ('Microsoft.SqlServer.Management.Smo.Server') Localhost
    $sqlesq.Settings.LoginMode = [Microsoft.SqlServer.Management.Smo.ServerLoginMode]::Mixed
    $sqlesq.Settings.DefaultFile = $data
    $sqlesq.Settings.DefaultLog = $logs
    $sqlesq.Settings.BackupDirectory = $backups
    $sqlesq.Alter() 

    # Re-enable the sa account and set a new password to enable login
    Invoke-Sqlcmd -ServerInstance Localhost -Database "master" -Query "ALTER LOGIN sa ENABLE" 

    Invoke-Sqlcmd -ServerInstance Localhost -Database "master" -Query "ALTER LOGIN sa WITH PASSWORD = '" + $args[0] + "'"


    Restore-SqlDatabase -ServerInstance Localhost -Database "OpenHack" -BackupFile "C:\SQLDATA\OpenHack.bak"

    # Restart the SQL Server service
    Restart-Service -Name "MSSQLSERVER" -Force

}

Enable-PSRemoting -Force
Set-NetFirewallRule -Name "WINRM-HTTP-In-TCP-PUBLIC" -RemoteAddress Any
Invoke-Command -ScriptBlock $configureSQLBlock -Credential $credential -ComputerName $env:COMPUTERNAME -ArgumentList $password
Disable-PSRemoting -Force

New-NetFirewallRule -DisplayName "SQL Server" -Direction Inbound -Protocol TCP -LocalPort 1433 -Action allow 
