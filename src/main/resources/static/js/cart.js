const app = angular.module('app', []);
app.controller('cartCtrl', function ($scope, $http) {
	$scope.cart = {
		items: [],
		checkAll: false,
		selectedItems: [],
		plus(id) {
			var item = this.items.find((item) => item.id == id);
			item.quantity++;
			this.saveToLocalStorage();
		},
		minus(id) {
			var item = this.items.find((item) => item.id == id);
			if (item.quantity == 1) {
				return;
			}
			item.quantity--;
			this.saveToLocalStorage();
		},
		add(id) {
			var item = this.items.find((item) => item.id == id);
			if (item) {
				item.quantity++;
				this.saveToLocalStorage();
			} else {
				$http.get(`rest/products/${id}`).then((resp) => {
					console.log(resp.data);
					resp.data.quantity = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				});
			}
		},
		setSelected(id) {
			let itemFound = this.items.find((item) => item.id == id);
			let index = this.selectedItems.findIndex((item) => item.id == id);
			if (index != -1) {
				this.selectedItems.splice(index, 1);
			} else {
				this.selectedItems.push(itemFound);
			}
		},
		remove(id) {
			console.log(id);
			var index = this.items.findIndex((item) => item.id == id);
			let selectedIndex = this.selectedItems.findIndex((item) => item.id == id);
			console.log(this.selectedItems);
			console.log(index);
			console.log(selectedIndex);
			if (confirm('Bạn có chắc muốn xóa sản phẩm này khỏi giỏ hàng ?')) {
				this.items.splice(index, 1);
				this.selectedItems.splice(selectedIndex, 1);
				this.saveToLocalStorage();
			}
		},
		selectAll() {
			this.checkAll = !this.checkAll;

			if (this.checkAll) {
				this.selectedItems = [...this.items];
				$scope.check = true;
			} else {
				this.selectedItems = [];
				$scope.check = false;
			}
		},
		get countSelectedItems() {
			return this.selectedItems.map((item) => item.quantity).reduce((total, quantity) => (total += quantity), 0);
		},
		get count() {
			return this.selectedItems.map((item) => item.quantity).reduce((total, quantity) => (total += quantity), 0);
		},
		get amount() {
			return this.selectedItems.map((item) => item.quantity * item.price).reduce((total, quantity) => (total += quantity), 0);
		},
		clear() {
			if (confirm('Bạn có chắc muốn xóa toàn bộ sản phẩm khỏi giỏ hàng ?')) {
				this.items = [];
				this.selectedItems = [];
				this.saveToLocalStorage();
			}
		},
		amt_of(items) {},
		saveToLocalStorage() {
			var cart = JSON.stringify(angular.copy(this.items));
			localStorage.setItem('cart', cart);
			var selectedItems = JSON.stringify(angular.copy(this.selectedItems));
			localStorage.setItem('selectedItems', selectedItems);
		},
		loadFormLocalStorage() {
			var json = localStorage.getItem('cart');
			this.items = json ? JSON.parse(json) : [];
			// this.selectedItems = [];
		},
		order() {
			this.items = this.items.filter((item) => !this.selectedItems.includes(item));
			this.saveToLocalStorage();
		},
	};
	$scope.cart.loadFormLocalStorage();
});
