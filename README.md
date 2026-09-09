## ESTRUTURA DE DIRETÓRIOS

src/

├─ model/          # Entidades e regras de domínio

├─ service/        # Regras de negócio e gerenciamento de dados

├─ view/           # Interface com o usuário (Menus e Scanner)

└─ Main.java       # Ponto de entrada da aplicação

## O que vai em cada pacote?

model/: Classes que representam o mundo real (ex: Evento, Participante, Ingresso). Devem possuir atributos private, construtores, getters/setters e validações internas.

service/: O "cérebro" da aplicação. Armazena as listas dinâmicas (ArrayList) e executa as operações de cadastro, busca e regras de negócio. Não interage diretamente com o terminal.

view/: Responsável por interagir com o usuário. Captura entradas via Scanner, exibe menus formatados e repassa os dados para o service.

Main.java: Apenas instancia a camada de visualização e inicia o fluxo do programa.
