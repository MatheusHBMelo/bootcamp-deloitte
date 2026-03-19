## 🆕 Changelog

### v0.01 (Task 1: Criar crud no console)
- Adicionado o repositório;
- Criada a estrutura inicial do projeto na IDE;
- Adicionado o menu interativo de usuário;
- Adicionadas as funções de CRUD para usuário;
- Adicionada validação para indices;
- Correção de erros de validação;
- Refatorada a logica do CRUD de IF-ELSE para SWITCH-CASE.

### v0.02 (Task 2: Adicionar POO ao CRUD)
- Criada a classe `Usuario` com os atributos (nome, email, senha);
- Criada a classe `GerenciadorUsuario` com a logica do CRUD de usuario;
- Refatoração da classe Main(SistemaUsuario) para o padrão orientado a objetos;
- Adiciona tratamento de exceção para o valor de entrada do menu.

### v0.03 (Task 3: Converter o projeto para Spring)
- Migração do projeto para arquitetura Spring Boot;
- Adicionado Maven como gerenciador de dependências;
- Configurado `pom.xml` com Spring Boot Starter Web, Dev Tools, Lombok, Spring Data e H2 Database;
- Configurado `application.properties` com a conexão do H2 Database;
- Renomeada a classe `SistemaUsuario` para `SistemaUsuarioApplication`;
- Renomeada a classe `GerenciadorUsuario` para `UsuarioService` e adiciona compatibilidade com banco de dados;
- Adicionada arquitetura de camadas com Service, Controller, Repository, Entity, Mapper, Dto e Exception;
- Habilitado os endpoints de CREATE, READ, UPDATE e DELETE;
- Adicionado tratamento global de exceções.

### v0.04 (Task 4: Criar testes unitários na branch de test)
- Adicionado 100% de cobertura de testes unitários para a classe `UsuarioController`;
- Adicionado 100% de cobertura de testes unitários para a classe `UsuarioService`.

### v0.05 (Task 5: Aplicar SRP e OCP do conceito SOLID)
- Adiciona os métodos `removerUsuario` e `deletarUsuario` como transações;
- Adiciona a interface `UsuarioValidation` com método para validar usuário;
- Adiciona a classe `UsuarioValidator` como orquestrador das validações do campo de usuário conforme OCP;
- Adiciona as classes `NomeValidator`, `EmailValidator`, `CPFValidator`, `TelefoneValidator` com sua propria logica de validação conforme OCP;
- Adiciona a classe `UsuarioExistenteValidator` que evita repetição do código de busca de usuário para os métodos de buscar, editar e remover;
- Adiciona a exceção personalizada `UsuarioValidationException` para tratar das validações dos atributos do usuário;
- Refatora a classe `UsuarioMapperImpl` para atualizar os campos do usuário, retirando esse trabalho do service conforme SRP;
- Adiciona a interface `UsuarioMapper` com os métodos de manipulação e mapeio do objeto Usuario tornando extensivel conforme OCP; 
- Refatora a classe `UsuarioService` delegando as funções de validação e mapeio para suas devidas classes conforme SRP;
- Adiciona validação para emails já existentes no método de adicionar novo usuário;
- Adiciona teste unitário para `testar o fluxo completo criando um novo usuário` do desafio Extra.

### v0.06 (Atualizações proprias autorizadas pelo professor)
- Adiciona um readme documentando o projeto;
- Corrige erros de escrita e incoerências;
- Adiciona anotação do Lombok em classes com construtor;
- Habilita Swagger UI no projeto.

### v0.07 (Task 6: Criação do frontend da aplicação)
- Cria página index.html com Bootstrap 5 e Alpine.js para consumo da API REST;
- Implementa formulário de cadastro de usuário com validação de campos obrigatórios;
- Implementa listagem de usuários em tabela;
- Implementa funcionalidade de edição de usuário;
- Implementa exclusão de usuário com modal de confirmação antes de deletar;
- Adiciona tratamento de mensagens de erro retornadas pelo backend;
- Adiciona paginação frontend com 7 registros por página;
- Adiciona formatação de CPF e telefone na exibição da tabela;
- Corrige validações do service para correta exibição de mensagens de erro no front.
