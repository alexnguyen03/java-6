const app = angular.module('app', []);
app.controller('cartCtrl', function ($scope, $http) {
	$scope.cart = {
		items: [],
		add(id) {
			var item = this.items.find((item) => item.id == id);
			if (item) {
				item.quantity++;
				this.saveToLocalStorage();
			} else {
				$http.get(`rest/products/${id}`).then((resp) => {
					resp.data.quantity = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				});
			}
		},
		remove(id) {},
		clear() {},
		amt_of(items) {},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem('cart', json);
		},
	};
});
