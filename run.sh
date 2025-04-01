#!/bin/bash

# Load environment variables from .env, ignoring comments and blank lines
export $(grep -v '^#' .env | grep -v '^$' | xargs)

# Set the profile dynamically (default to 'dev' if no argument is provided)
SPRING_PROFILES_ACTIVE=${1:-dev}
export SPRING_PROFILES_ACTIVE # Export the variable to make it available to subshells

echo "Running application with profile: $SPRING_PROFILES_ACTIVE"

# Run the application
mvn spring-boot:run -Dspring-boot.run.profiles=$SPRING_PROFILES_ACTIVE
