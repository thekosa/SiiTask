@echo off
set API_URL=http://localhost:8080

echo Tworzenie wydarzen...
curl -X POST %API_URL%/events/new -H "Content-Type: application/json" -d "{\"name\":\"WOSP Warszawa\",\"currency\":\"PLN\"}"
echo.

curl -X POST %API_URL%/events/new -H "Content-Type: application/json" -d "{\"name\":\"Pomoc Ukrainie\",\"currency\":\"EUR\"}"
echo.

echo Tworzenie skarbonek...
curl -X POST %API_URL%/boxes/new -H "Content-Type: application/json" -d "{\"name\":\"Skarbonka 1\"}"
echo.

curl -X POST %API_URL%/boxes/new -H "Content-Type: application/json" -d "{\"name\":\"Skarbonka 2\"}"
echo.

echo Przypisywanie skarbonek do wydarzen...
curl -X PUT %API_URL%/boxes/1/assign-event/1
echo.

curl -X PUT %API_URL%/boxes/2/assign-event/2
echo.

echo Dodawanie darowizn...
curl -X POST %API_URL%/donations/1/new -H "Content-Type: application/json" -d "{\"amount\":100,\"currency\":\"PLN\"}"
echo.

curl -X POST %API_URL%/donations/1/new -H "Content-Type: application/json" -d "{\"amount\":50,\"currency\":\"USD\"}"
echo.

curl -X POST %API_URL%/donations/2/new -H "Content-Type: application/json" -d "{\"amount\":200,\"currency\":\"EUR\"}"
echo.

echo Przelew srodkow ze skarbonek do wydarzen...
curl -X PUT %API_URL%/boxes/transfer/1
echo.

curl -X PUT %API_URL%/boxes/transfer/2
echo.

echo Generowanie raportu...
curl %API_URL%/events/report
echo.

pause
