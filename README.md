# bookstore-EMT
Simple bookstore application for managing books and book copies.

## Instructions for running this project under docker:
<ol>
  <li>Navigate to the <code>.env</code> file in the root directory and enter your desired credentials.</li>
  <li>If you have the maven executable run <code>mvn clean install</code></li>
  <li>If you have intellij run <code>clean</code> and <code>install</code> from the maven menu</li>
  <li>Run <code>docker-compose --env-file .env up -d</code></li>
  <li>You should now be able to access the three containers:
    <ol>
      <li>UI on <code>localhost:3001</code></li>
      <li>API on <code>localhost:8080</code></li>
      <li>Postgres db on <code>localhost:5432</code></li>
    </ol>
  </li>
</ol>

## Instructions for running this project without docker:
<ol>
  <li>
    Navigate to the <code>api/src/main/resources/application.properties</code> file and enter the database credentials of your postgres db
  </li>  
  <li>
    Navigate to the <code>ui/src/setupProxy.js</code> file and edit the line <code>"http://bookstore-api:8080"</code> to reflect to the location and port to the running api 
    (ex. <code>"http://localhost:8080"</code>) 
  </li>
  <li>
    IMPORTANT NOTE: You will need to start the api (spring boot) application first before starting the ui (React) application so that the proxy gets created properly otherwise it might not work
  </li>
</ol>
