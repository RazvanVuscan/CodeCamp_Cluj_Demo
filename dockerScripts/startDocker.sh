

#!/usr/bin/env bash

eval "$(docker-machine env default)"

if ! curl http://192.168.99.100:4444/grid/console > /dev/null 2>&1

then
    echo "Starting the Grid."

    docker run -d -p 4444:4444 --name selenium-hub selenium/hub:3.5.3
    docker run -d -p 5900:5900 --name selenium-chrome-debug -v /dev/shm:/dev/shm \
        -e no_proxy=localhost -e HUB_ENV_no_proxy=localhost --link selenium-hub:hub selenium/node-chrome-debug:3.5.3
    docker run -d -p 5901:5900 --name selenium-firefox-debug \
        -e no_proxy=localhost -e HUB_ENV_no_proxy=localhost --link selenium-hub:hub selenium/node-firefox-debug:3.5.3

    echo "Waiting for Grid to load."

    while ! curl http://192.168.99.100:4444/grid/console > /dev/null 2>&1

    do
        echo "."
        sleep 1
    done

    sleep 5

    echo " "
fi

echo "Connected to grid successfully!"



