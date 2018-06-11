var app = angular.module('controlador',[]);

app.controller('abmCtrl', ['$scope', '$http', 'servicios', function($scope, $http, servicios) {

	$scope.empleado = {};

	$scope.empleados = [];

	$scope.listarEmpleados = function () {
		servicios.get(function (success) {
			$scope.empleados = success.data;
		});
	}

	$scope.crearEmpleado = function () {
		servicios.crear($scope.empleado, 

		function (success){
			$scope.limpiar();
			$scope.listarEmpleados();
		}, function (failure){
			if (failure.status < 0) {
				alert('No se ha recibido respuesta del servidor');
			} else {
				alert('Ha habido un error con los datos ingresados');
			}

			$scope.limpiar();
		});
		
	}

	$scope.editarEmpleado = function () {	
		servicios.editar($scope.empleado, 

		function (success){
			$scope.listarEmpleados();
			$scope.limpiar();
		}, function (failure){

			if (failure.status < 0) {
				alert('No se ha recibido respuesta del servidor');
			} else {
				alert(failure.data.mensaje);
			}

			$scope.limpiar();
		});
	}

	$scope.idEliminar = function(emp){
		$scope.empleado = emp;
	}

	$scope.eliminarEmpleado = function () {

		servicios.eliminar($scope.empleado, 

		function (success){
			$scope.listarEmpleados();
			$scope.limpiar();
		}, function (failure){

			if (failure.status < 0) {
				alert('No se ha recibido respuesta del servidor');
			} else {
				alert(failure.data.mensaje);
			}

			$scope.limpiar();
		});

	}

	$scope.modalEditar = function(emp) {
		$scope.empleado = angular.copy(emp);
	}

	$scope.limpiar = function () {
		$scope.empleado = {};
		$scope.buscar = {};
		$scope.formularioCrear.$setPristine();
		$scope.formularioEditar.$setPristine();
	}

	$scope.esCorrectoNombre = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.nombre.$error.required;
		}
		return !$scope.formularioEditar.nombre.$error.required;
	}

	$scope.esIncorrectoNombre = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.nombre.$pristine && $scope.formularioCrear.nombre.$error.required;
		}
		return !$scope.formularioEditar.nombre.$pristine && $scope.formularioEditar.nombre.$error.required;
	}

	$scope.esCorrectoEdad = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.edad.$error.required 
			&& !$scope.formularioCrear.edad.$error.min && !$scope.formularioCrear.edad.$error.max;
		}
		return !$scope.formularioEditar.edad.$error.required 
		&& !$scope.formularioEditar.edad.$error.min && !$scope.formularioEditar.edad.$error.max;
	}

	$scope.esIncorrectoEdad = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.edad.$pristine && 
			($scope.formularioCrear.edad.$error.required || $scope.formularioCrear.edad.$error.min || $scope.formularioCrear.edad.$error.max);
		}
		return !$scope.formularioEditar.edad.$pristine && 
		($scope.formularioEditar.edad.$error.required || $scope.formularioEditar.edad.$error.min || $scope.formularioEditar.edad.$error.max);
	}

	$scope.esIncorrectoEdadSpan = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.edad.$pristine && $scope.formularioCrear.edad.$error.required;
		}
		return !$scope.formularioEditar.edad.$pristine && $scope.formularioEditar.edad.$error.required;
	}

	$scope.esCorrectoSueldo = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.sueldo.$error.required 
			&& !$scope.formularioCrear.sueldo.$error.min && !$scope.formularioCrear.sueldo.$error.max;
		}
		return !$scope.formularioEditar.sueldo.$error.required 
		&& !$scope.formularioEditar.sueldo.$error.min && !$scope.formularioEditar.sueldo.$error.max;
	}	

	$scope.esIncorrectoSueldo = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.sueldo.$pristine && 
			($scope.formularioCrear.sueldo.$error.required || $scope.formularioCrear.sueldo.$error.min || $scope.formularioCrear.sueldo.$error.max);
		}
		return !$scope.formularioEditar.sueldo.$pristine && 
		($scope.formularioEditar.sueldo.$error.required || $scope.formularioEditar.sueldo.$error.min || $scope.formularioEditar.sueldo.$error.max);
	}

	$scope.esIncorrectoSueldoSpan = function (editarCrear){
		if(editarCrear.localeCompare('Crear') == 0){
			return !$scope.formularioCrear.sueldo.$pristine && $scope.formularioCrear.sueldo.$error.required;
		}
		return !$scope.formularioEditar.sueldo.$pristine && $scope.formularioEditar.sueldo.$error.required;
	}

	$scope.listarEmpleados();

}]);