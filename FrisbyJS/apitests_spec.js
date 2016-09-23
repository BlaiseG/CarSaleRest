var frisby = require('frisby');
frisby.create('Test finding admin user')
  .get('http://localhost:8080/api/users/search/findByUsername?username=admin')
  .auth("admin","admin")
  .expectStatus(200)
  .expectHeaderContains('content-type', 'application/hal+json')
  .inspectBody()
  .expectJSON({
	  username: 'admin',
	  authorities: [{
		  authority: "ROLE_ADMIN"
	  }]
  })
.toss()

frisby.create('Check for valid modelName and purchaseDate')
  .get('http://localhost:8080/api/cars/4')
  .auth("admin","admin")
  .expectStatus(200)
  .expectHeaderContains('content-type', 'application/hal+json')
  .inspectBody()
  .expectJSON({
	  modelName: 'BMW',
	  purchaseDate: function(val) {
		var purchaseDate = new Date(val);
		expect(purchaseDate).toBeDefined();
	  }
  })
.toss()

