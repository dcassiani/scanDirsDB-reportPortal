// invocado pelo angular.module('imgScanApp') e pelo próprio .run()
var imgScanApp = angular.module('imgScanApp', []); // declaração do modulo myApp

imgScanApp.filter('imgRegex', function(){
	return function (inputArr, optSelection){
		var out = [];
		var attr = 'atrib';
		var full = 'fullImage';
		var thumb = 'thumbnail';
		
		//alert(optSelection + ' - '+ inputArr.length)
		
		// TODOS = 0 = retorna tudo o que veio
		if (optSelection == '0' ) {
			return inputArr;
			
		// Thumbnail = T = retorna só quem tem thumbnail
		} else if(optSelection == 'T' ){
			scanArrayAndPushFlagedFields (inputArr, out, thumb, false)
			
		// fullImage = F = retorna só quem tem fullImage
		} else if(optSelection == 'F' ){
			scanArrayAndPushFlagedFields (inputArr, out, full, false);

		} else if(optSelection == 'A' ){
			scanArrayAndPushFlagedFields (inputArr, out, attr, false);
		}
		

		// retorno exclusivo do T, A e F
		return out;
	};
	
	function scanArrayAndPushFlagedFields (inputArr, out, field, flag){
		var booleanDefaultAttr = 'ready';
		for (var i=0; i < inputArr.length; i++){
			if ( inputArr[i][field][booleanDefaultAttr] == flag ){
				//alert(inputArr[i]['name']);
				out.push(inputArr[i]);
			}
		}
		return out;
	}
});


imgScanApp.controller('ImgScanControl',  ['$scope', '$http', function($scope, $http){

	$scope.tipoArr = [  
		 { label: 'Todos', val: '' },
         { label: 'Produto', val: 'Produto' },  
		 { label: 'Item', val: 'Item' }
		 { label: 'Atributo', val: 'Atributo' }
       ]; 
	   
	$scope.pagingArr = [ 
         { label: '15', val: '15' },  
		 { label: '50', val: '50' }, 
		 { label: '100', val: '100' }, 
		 { label: '500', val: '500' }, 
		 { label: 'Todos', val: '999999999' }
    ];

	$scope.imgArr = [  
         { label: 'Todos', val: '0' },
		 { label: 'Thumbnail', val: 'T' },  
		 { label: 'Atributos', val: 'A' },
		 { label: 'FullImage', val: 'F' }
   ]; 
	
	$scope.loadData = function () {
		var target = $scope.productList;
		var tipo = $scope.tipoList;
		$http({
			method:'GET', 
			url:'http://ryelxwebebrdap2.rye.avon.com/img-scan/json/'+target, 
		//	url:'../json/'+target, 
			cache: false
		})
			.success(function (data){
				$scope.campaign = angular.fromJson(data);
			});
		
	};
	

	$http({
		method:'GET', 
		url:'http://ryelxwebebrdap2.rye.avon.com/img-scan/json/index.json',
	//	url:'../json/index.json',
		cache: false
	})
		.success(function (data, status, headers, config){
			$scope.json = angular.fromJson(data);
			$scope.lastMod = headers("last-modified");
			
			// initial Load
			$scope.productList = $scope.json[0].jsonName;
			$scope.tipoList = $scope.tipoArr[0].val;
			$scope.filterPaging = $scope.pagingArr[0].val;
			$scope.filtroImg = $scope.imgArr[0].val;
			$scope.loadData();
		});
	


}]);


