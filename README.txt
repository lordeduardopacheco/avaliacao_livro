Projeto desenvolvido utilizando banco de dados H2

Passo 1:
Executar o projeto.
Observação: Ao rodar o projeto todos os dados presentes no arquivo CSV contido na pasta: 
src/main/resources/movielist.csv
Serão carregados automaticamente na base de dados em memória H2. 
Para rodar com outros arquivo, basta substituir o mesmo. 

Passo 2:
Chamar endpoint para buscar os intervalos solicitados.

Curl:
curl --location --request GET 'http://localhost:8080/api/awards/intervals'

Passo 3:
Rodar testes unitários. 
Testes unitários desenvolvidos para testes de integração e estão localizados em:
src/test/java/br/avaliacao/test/controller/AwardControllerTest.java
