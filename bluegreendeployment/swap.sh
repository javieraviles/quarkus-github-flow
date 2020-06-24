#!/bin/bash
inactive_now=$(ls -l ./ | grep inactive)
if [[ "$inactive_now" == *blue ]]
then
  inactive="blue"
  active="green"
else
  inactive="green"
  active="blue"
fi

#remove current links
rm ./available
rm ./inactive
rm -f /etc/nginx/nginx.conf
#create new links with the active/inactive reversed
ln -s ./$inactive ./available
ln -s ./$active ./inactive
ln -s /home/ubuntu/p2cd/$active/nginx.conf /etc/nginx/nginx.conf
#reload the http server
service nginx reload
echo swap completed $active is now available