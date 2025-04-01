#!/bin/bash
set -e

echo "Starting entrypoint.sh script..."

# Log the value of MYSQL_HOST
echo "MYSQL_HOST: ${MYSQL_HOST}"

# Only echo sensitive variables in test or development environments
if [[ "$SPRING_PROFILES_ACTIVE" == "test" || "$SPRING_PROFILES_ACTIVE" == "dev" ]]; then
    echo "SPRING_PROFILES_ACTIVE: ${SPRING_PROFILES_ACTIVE}"
    echo "SPRING_DATASOURCE_USERNAME: ${SPRING_DATASOURCE_USERNAME}"
    echo "SPRING_DATASOURCE_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}"
    echo "MYSQL_HOST: ${MYSQL_HOST}"
else
    echo "Sensitive environment variables are hidden in this environment."
fi

echo "Testing MySQL connection..."
for i in {1..60}; do
    if mysqladmin ping -h"${MYSQL_HOST}" --silent; then
        echo "MySQL is up - executing command"
        exec "$@"
        break
    fi
    echo "MySQL is unavailable - sleeping 3sec"
    sleep 3
done

echo "MySQL did not become available in time - exiting"
exit 1