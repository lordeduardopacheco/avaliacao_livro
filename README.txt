Projeto desenvolvido utilizando banco de dados H2

Passo 1:
Executar o projeto.
Observação: Ao rodar o projeto de forma automatica a base de dados será preenchida conforme arquivo CSV.

Passo 2:
Chamar endpoint para buscar os intervalos solicitados.

Curl:
curl --location --request GET 'http://localhost:8080/api/awards/intervals'

Passo 3:
Rodar testes unitários. 
Testes unitários desenvolvidos para testes de integração e estão localizados em:
src/test/java/br/avaliacao/test/controller/AwardControllerTest.java
