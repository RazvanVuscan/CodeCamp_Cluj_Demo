#!/usr/bin/env bash

eval "$(docker-machine env default)"

echo -n "Stopping the Grid."

echo

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

echo -n "Grid stopped."



