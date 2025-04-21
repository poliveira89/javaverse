#!/usr/bin/env bash

# Working directory
CWD="../"
echo " Building JavaVerse with context: ${CWD}"

# Retrieve the Project version with mvn
VERSION=$(xmllint --xpath 'string(//*[local-name()="project"]/*[local-name()="version"])' "${CWD}pom.xml")
echo "Suggested version: ${VERSION}"

# Create JAR
mvn clean package -DskipTests -q -f "${CWD}pom.xml"
echo "Create JAR artifact"

# Copy artifact
cp "${CWD}target/"*.jar app.jar

# Build Docker image
docker build -t javaverse-tasks:"$VERSION" .
echo "Image created"

rm -f "app.jar"
echo "Clean up JAR artifact"