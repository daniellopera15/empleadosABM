var app = angular.module('directivas',[]);

app.directive('modalCrear', function(){
    return{
        restrict: 'E',
        templateUrl: 'pages/crearModal.html'
    };
});

app.directive('modalEditar', function(){
    return{
        restrict: 'E',
        templateUrl: 'pages/editarModal.html'
    };
});

app.directive('modalEliminar', function(){
    return{
        restrict: 'E',
        templateUrl: 'pages/eliminarModal.html'
    };
});