var app = angular.module('services',[]);

app.service('servicios', ['$http', function($http){
	this.get = function(success, failure){
		$http.get("http://localhost:8080/empleado/list")
		.then(success,failure);
	}

	this.crear = function(empleado, success, failure){
		$http.post("http://localhost:8080/empleado/crear", empleado)
		.then(success,failure);
	}

	this.editar = function(empleado, success, failure){
		$http.put("http://localhost:8080/empleado/editar", empleado)
		.then(success,failure);
	}

	this.eliminar = function(empleado, success, failure){
		$http.delete("http://localhost:8080/empleado/eliminar/" + empleado.id)
		.then(success,failure);
	}
}]);