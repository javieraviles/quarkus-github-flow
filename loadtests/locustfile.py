import datetime
from http import HTTPStatus
from locust import HttpUser, task, between

# This test can be run after installing locust through the cli as "locust --host=http://<deployed_host>:<port>"
# Then url http://localhost:8089/ should be access to start the test.
# Can also be run using no UI mode as "locust --no-web -c <number_of_clients> -r <clients_per_second> --run-time <time e.g. 1h30m> --host=http://<deployed_host>:<port>"

class QuickstartUser(HttpUser):
    wait_time = between(1, 2)

    @task(1)
    def get_hello(self):
        r = self.client.get("/hello")
        assert r.status_code == HTTPStatus.OK, "Unexpected response code: " + str(r.status_code)
        assert r.elapsed < datetime.timedelta(seconds=0.5), "Request took more than 0.5 second"

