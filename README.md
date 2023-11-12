
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 600 200" width="300" height="100">  <!-- Retângulo preto como fundo -->  <rect width="100%" height="100%" fill="black" />  <!-- Conteúdo original da imagem -->  <image href="https://devsuperior.com.br/_next/static/images/logo-062c6633a2eb15831642636f96aa2c6c.svg" width="80%" height="80%" x="10%" y="10%" />  </svg>


## DESAFIO: CRUD de clientes

### Capítulo: API REST, camadas, CRUD, exceções, validações

Projeto Spring Boot contendo um CRUD completo de web services REST para acessar um recurso de clientes, contendo as
cinco operações básicas aprendidas no capítulo:

- Busca paginada de recursos
- Busca de recurso por id
- Inserir novo recurso
- Atualizar recurso
- Deletar recurso

O projeto deverá estar com um ambiente de testes configurado acessando o banco de dados H2, deverá usar Maven como
gerenciador de dependência, e Java como linguagem. Um cliente possui nome, CPF, renda, data de nascimento, e quantidade
de filhos. A especificação da entidade Client é mostrada a seguir (você deve seguir à risca os nomes de classe e
atributos mostrados no diagrama):

<table>
	<tr style="background-color:#FEFDCD; color:black"><th>Clients</th></tr>
    <tbody style="background-color:#FEFDCD; color:black" >
        <tr><td style="color:black">-id: Long</td></tr>
        <tr><td style="color:black">-name: String</td></tr>
        <tr><td style="color:black">-cpf: String</td></tr>
        <tr><td style="color:black">-income: Double</td></tr>
        <tr><td style="color:black">-birthDate: LocalDate</td></tr>
        <tr><td style="color:black">-children: Integer</td></tr>
    </tbody>
</table>

---

Seu projeto deverá fazer um seed de pelo menos 10 clientes com dados SIGNIFICATIVOS (não é para usar dados sem significado como “Nome 1”, “Nome 2”, etc.).

#### Projeto deverá tratar as seguintes exceções:
- Id não encontrado (para GET por id, PUT e DELETE), retornando código 404.
- Erro de validação, retornando código 422 e mensagens customizada para cada campo inválido. As regras de validação são:
    - Nome: não pode ser vazio
    - Data de nascimento: não pode ser data futura (dica: use @PastOrPresent)