# spring-boot-access-token
<h2>An implementation of secure Rest API's with spring boot 3</h2>. 
<br> <h3>Technology used</h3>
<ul>
  <li>Spring security 3</li>
   <li>Lombock</li>
   <li>PostgreSQL</li>
   <li>Docker-compose</li>
   <li>JPA</li>
   <li>JavaMailSender</li>
</ul>
<div>
  <p>In the ap we can register, and authenticate each step have access token that is provide</p>
  <p>To access an resource you need to have authorization <b>ACL</b> so system will check if you've right for that resource</p>
</div>
<h3>Important app ressource</h3>
<p>
<ul>
  <li> <a href="localhost:8081/api/v1/auth/register">localhost:8081/api/v1/auth/register</a></li>
  <li > <a href="localhost:8081/api/v1/auth/authenticate">localhost:8081/api/v1/auth/authenticate</a></li>
  <li > <a href="localhost:8081/api/v1/app/addRole">localhost:8081/api/v1/app/addRole</a></li>
  <li > <a href="localhost:8081/api/v1/app/addRoleToUser">localhost:8081/api/v1/app/addRoleToUser</a></li>
  <li > <a href="localhost:8081/api/v1/demo">localhost:8081/api/v1/demo</a></li>
</ul>
</p>
<b>Important to nkow to forget to provide access token at header befaore access a resource</b>
<b>Use <a href="https://www.postman.com/">Postman</a> or any client you to test it</b>
<div>
  <h3>Basic cmd to run docke-compose</h3>
  <b>Make sure you have <a href="https://docs.docker.com/">docker</a> and <a href="https://www.postgresql.org/">PostgreSQL</a> instal
  <ol>
    <li>$ docker compose up -d</li>
    <li>$ docker exec -it applog bash</li>
    <li>$ -U postgres-user psql</li>
     <li>$ CREATE DATABASE your database-name</li>
  </ol>
    <p>Go back to application.yml update some information like database, user-database, password-database</p>
    <p>Let me know if you have any question otherWhile update as you want. Add email reggex in emailValidato.java also cutomise email sender.</p>
</div>
