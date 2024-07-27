var app = angular.module("app", []);
app.controller("ctrl", function($scope, $http) {
    $http.get("/rest/authorities").then(resp => {
        $scope.db = resp.data;
       console.log(resp.data);
    });
    $scope.index_of = function(username, roleid) {
        return $scope.db.authorities.findIndex(a => a.account.username == username && a.role.id == roleid);
    }

    $scope.update = function(username, roleid) {
        var index = $scope.index_of(username, roleid);
        if (index >= 0) {
            var id = $scope.db.authorities[index].id;
            $http.delete(`/rest/authorities/${id}`).then(resp => {
                $scope.db.authorities.splice(index, 1);
            }).catch(error => {
                console.error("Error deleting authority:", error);
            });
        } else {
            var authority = {
                account: { username: username },
                role: { id: roleid }
            };
            $http.post('/rest/authorities', authority).then(resp => {
                $scope.db.authorities.push(resp.data);
            }).catch(error => {
                console.error("Error creating authority:", error);
            });
        }
    }
});
