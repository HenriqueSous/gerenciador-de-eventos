## ESTRUTURA DE DIRETÓRIOS

src/

├─ com.ifba.sistema.model/          # Entidades e regras de domínio

├─ com.ifba.sistema.service/        # Regras de negócio e gerenciamento de dados

├─ com.ifba.sistema.view/           # Interface com o usuário (Menus e Scanner)

└─ Main.java       # Ponto de entrada da aplicação

## O que vai em cada pacote?

com.ifba.sistema.model/: Classes que representam o mundo real (ex: Evento, Participante, Ingresso). Devem possuir atributos private, construtores, getters/setters e validações internas.

com.ifba.sistema.service/: O "cérebro" da aplicação. Armazena as listas dinâmicas (ArrayList) e executa as operações de cadastro, busca e regras de negócio. Não interage diretamente com o terminal.

com.ifba.sistema.view/: Responsável por interagir com o usuário. Captura entradas via Scanner, exibe menus formatados e repassa os dados para o com.ifba.sistema.service.

Main.java: Apenas instancia a camada de visualização e inicia o fluxo do programa.
