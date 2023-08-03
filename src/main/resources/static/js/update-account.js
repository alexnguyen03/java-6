/**
 * 
 */
var app = angular.module('myApp_Update_Account', []);
app.controller('myCtrl_Update_Account', function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	
	// Load account
	$scope.initialize = function(){
		$http.get("/rest/update-account").then(resp => {
			$scope.form = angular.copy(resp.data);
		})
	}
	
	$scope.initialize();
	
    // Upload hình
    $scope.imageChanged = function(files){
		var data = new FormData();
		data.append("file", files[0]);
		$http.post("/rest/upload/update-account", data, {
			transformRequest: angular.identity,
			headers: {"Content-Type": undefined}
		}).then(resp => {
			$scope.form.photo = resp.data.name;
			console.log($scope.form.photo);
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}
	
	// Cập nhật sản phẩm
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put("/rest/update-account/${item.username}", item).then(resp => {
			var index = $scope.items.findIndex(p => p.username === item.username);
			console.log(item.photo);
			$scope.items[index] = item;
			alert("Cập nhật tài khoản thành công!!");
		})
		.catch(error => {
			alert("Lỗi cập nhật tài khoản!!");
			console.log("Error", error);
		});
	}
});