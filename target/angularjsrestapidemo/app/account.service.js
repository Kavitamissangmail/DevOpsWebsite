angular.module('crudApp').factory('Account', Account);

Account.$inject = [ '$resource' ];

function Account($resource) {
	var resourceUrl = 'api/account/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}