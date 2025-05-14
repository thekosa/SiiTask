#!/bin/bash

API_URL="http://localhost:8080"

echo "Tworzenie wydarzeń..."
curl -s -X POST "$API_URL/events/new" -H "Content-Type: application/json" -d '{"name":"WOSP Warszawa","currency":"PLN"}'
echo

curl -s -X POST "$API_URL/events/new" -H "Content-Type: application/json" -d '{"name":"Pomoc Ukrainie","currency":"EUR"}'
echo

echo "Tworzenie skarbonek..."
curl -s -X POST "$API_URL/boxes/new" -H "Content-Type: application/json" -d '{"name":"Skarbonka 1"}'
echo

curl -s -X POST "$API_URL/boxes/new" -H "Content-Type: application/json" -d '{"name":"Skarbonka 2"}'
echo

echo "Przypisywanie skarbonek do wydarzeń..."
curl -s -X PUT "$API_URL/boxes/1/assign-event/1"
echo

curl -s -X PUT "$API_URL/boxes/2/assign-event/2"
echo

echo "Dodawanie darowizn..."
curl -s -X POST "$API_URL/donations/1/new" -H "Content-Type: application/json" -d '{"amount":100,"currency":"PLN"}'
echo

curl -s -X POST "$API_URL/donations/1/new" -H "Content-Type: application/json" -d '{"amount":50,"currency":"USD"}'
echo

curl -s -X POST "$API_URL/donations/2/new" -H "Content-Type: application/json" -d '{"amount":200,"currency":"EUR"}'
echo

echo "Przelew środków ze skarbonek do wydarzeń..."
curl -s -X PUT "$API_URL/boxes/transfer/1"
echo

curl -s -X PUT "$API_URL/boxes/transfer/2"
echo

echo "Generowanie raportu..."
curl -s "$API_URL/events/report"
echo
