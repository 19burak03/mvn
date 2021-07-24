#!/bin/sh -l
javac -cp lib/* Main.java
echo "wget"
wget "https://download.hazelcast.com/management-center/hazelcast-management-center-4.2021.04.tar.gz" -O ./man.tar.gz
ls -la
echo "Root Directory"
cd ./
mkdir -p ./mancenter
ls -la
tar -xvzf man.tar.gz -C ./mancenter
rm -rf man.tar.gz
cd ./mancenter/hazelcast-management-center-4.2021.04
ls -la
java -jar hazelcast-management-center-4.2021.04.jar & -Xms2g -Xmx2g -cp ".:lib/*" Main
