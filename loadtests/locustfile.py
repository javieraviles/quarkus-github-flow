import datetime, urllib3, ssl, os
from http import HTTPStatus, client
from locust import HttpLocust, TaskSet, task

# This test can be run after installing locust through the cli as "locust --host=http://<deployed_host>:<port>"
# Then url http://localhost:8089/ should be access to start the test.
# Can also be run using no UI mode as "locust --no-web -c <number_of_clients> -r <clients_per_second> --run-time <time e.g. 1h30m> --host=http://<deployed_host>:<port>"

# this will disable ssl disabled warnings
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

partId = ''


class UserBehavior(TaskSet):

    # disable SSL verification for every request
    def on_start(self):
        self.client.verify = False

    @task(1)
    def get_hello(self):
        r = self.client.get("/hello")
        assert r.status_code == HTTPStatus.OK, "Unexpected response code: " + str(r.status_code)
        assert r.elapsed < datetime.timedelta(seconds=0.5), "Request took more than 0.5 second"


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 1000
    max_wait = 1000
