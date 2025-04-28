#!/bin/bash
set -e

echo "🚀 Building the project..."
./mvnw clean package

echo "🏃 Running the application..."
java -jar target/*.jar