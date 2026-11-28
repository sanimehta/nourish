# Posts every meal in seed-meals.json to the running Nourish API.
# Usage (app must be running): .\seed-meals.ps1
$uri  = "http://localhost:8080/api/meals"
$file = Join-Path $PSScriptRoot "seed-meals.json"
$meals = Get-Content -Raw -Path $file | ConvertFrom-Json

foreach ($m in $meals) {
    $body = $m | ConvertTo-Json
    try {
        $r = Invoke-RestMethod -Method Post -Uri $uri -ContentType "application/json" -Body $body
        Write-Host ("Created id {0}: {1} ({2}, {3})" -f $r.id, $r.name, $r.mealType, $r.dietType)
    } catch {
        Write-Host ("FAILED: {0} -> {1}" -f $m.name, $_.Exception.Message) -ForegroundColor Red
    }
}
