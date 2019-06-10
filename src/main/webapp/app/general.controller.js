angular.module("crudApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'Account' ];

function GeneralController($scope, Account) {

	$scope.accounts = Account.query();

	$scope.account = {};

	$scope.buttonText = "Submit";

	$scope.clearText = function() {
		$scope.accounts.text = null;
	}

	$scope.saveAccount = function() {
		if ($scope.account.id !== undefined) {
			Account.update($scope.account, function() {
				$scope.accounts = Account.query();
				$scope.account = {};
				$scope.buttonText = "Submit";
				$scope.isDisabled = false;

			});
		} else {
			Account.save($scope.account, function() {
				$scope.accounts = Account.query();
				$scope.account = {};

			});
		}
	}

	$scope.updateAccountInit = function(account) {
		$scope.buttonText = "Update";
		$scope.isDisabled = true;
		$scope.account = account;
	}

	$scope.deleteAccount = function(account) {
		account.$delete({
			id : account.id
		}, function() {
			$scope.accounts = Account.query();
		});
	}

}