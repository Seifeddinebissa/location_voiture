const Eureka = require('eureka-js-client').Eureka;

// Configure Eureka client
const client = new Eureka({
  instance: {
    app: 'OPENAI-SERVICE', // Name of the service
    hostName: 'localhost', // Hostname of the Node.js app
    ipAddr: '127.0.0.1', // IP address of the Node.js app
    port: {
      '$': 5000, // Port your Express app runs on
      '@enabled': 'true',
    },
    vipAddress: 'openai-service-test',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    host: 'localhost', // Eureka server host
    port: 8765, // Eureka server port
    servicePath: '/eureka/apps/', // Default Eureka service path
  },
});

// Start the Eureka client
client.start((error) => {
  if (error) {
    console.error('Eureka client failed to start:', error);
  } else {
    console.log('Eureka client started successfully');
  }
});

module.exports = client;