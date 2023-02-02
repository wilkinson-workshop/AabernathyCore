#!/bin/bash

find $(pwd)/build/libs -iname "*.jar" | xargs -I {} \
    rsync -avz {} \
    $MINECRAFTD_USER@$MINECRAFTD_HOST:~/server/$MCSERVER_ENVIRON/plugins

# We don't want to auto reload the production server.
if [[ $MCSERVER_ENVIRON == "prod" ]]; then
    exit 0
fi

ssh $MINECRAFTD_USER@$MINECRAFTD_HOST /home/$MINECRAFTD_USER/server/bin/mcrcon \
    -H localhost    \
    -P $MCRCON_PORT \
    -p $MCRCON_PASS \
    reload
