/**
 * 
 */
var app = angular.module('myApp_Update_Account', []);
app.controller('myCtrl_Update_Account', function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.photo = '';

	// Load account
	$scope.initialize = function() {
		$http.get("/rest/update-account").then(resp => {
			$scope.photo = "/img/update-account/" + resp.data.photo;
			$scope.form = angular.copy(resp.data);
		})
	}

	$scope.initialize();

	var image;

	// Upload hình
	$scope.imageChanged = function(files) {
		image = files[0];
		var file = files[0];
		var reader = new FileReader();

		reader.onload = function(e) {
			$scope.$apply(function() {
				$scope.photo = e.target.result; // Lưu đường dẫn hình ảnh vào $scope.photo
			});
		};
		$scope.form.photo = file.name;
		reader.readAsDataURL(file);
	}

	// Cập nhật sản phẩm
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var formData = new FormData();
		formData.append('file', image);

		// lưu vào database
		$http.put("/rest/update-account/${item.username}", item).then(resp => {
			var index = $scope.items.findIndex(p => p.username === item.username);
			$scope.items[index] = item;
			alert("Cập nhật tài khoản thành công!!");
		})
			.catch(error => {
				alert("Lỗi cập nhật tài khoản!!");
				console.log("Error", error);
			});

		// lưu ảnh vào thư mục
		$http.post("/rest/upload/update-account", formData, {
			transformRequest: angular.identity,
			headers: { "Content-Type": undefined }
		}).then(resp => {
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}
});