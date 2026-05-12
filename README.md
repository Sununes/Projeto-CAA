# Projeto-CAA
Este projeto vai ser desenvolvido por um grupo de 3 elementos .
## Tema: Sistema de Gestão de Atendimento Bancário.
O objetivo deste sistema é simular o funcionamento de atendimento num banco, onde
as pessoas tiram senhas para serem atendidas.
O sistema teria senhas normais e prioritárias, e também a possibilidade de a pessoa tirar a
senha presencialmente ou online.
Além disso, o cliente poderia ver quantas pessoas estão à frente dele na fila, e o sistema
também chamaria o próximo cliente para atendimento.
A estrutura de dados Fila (Queue), porque combina com a lógica de atendimento: primeiro
a entrar, primeiro a sair.
Primeiramente vamos desenvolver toda a lógica do sistema e só depois fazer a interface
gráfica.
## As suas funcionalidades são:
Gerar senha;
listar filas;
Atender próximo cliente;
Cancelar ( atender proximo) senha;
Consultar posição na fila.

Para guardar os dados, podemos usar ficheiros (Base de dados), porque provavelmente é mais
simples do que usar base de dados, pois o tempo não será suficiente para fazer muitas
pesquisas por fora .
Achamos que este é um tema útil, não muito complicado e que dá para cumprir tudo o que o
professor nos pediu.

# Descrição do sistema

O projeto consiste no desenvolvimento de um Sistema de Gestão de Atendimento Bancário,
com o objetivo de organizar o atendimento de clientes através de senhas.
O sistema permitirá gerar senhas normais e prioritárias, organizar os clientes em filas e
chamar o próximo cliente para atendimento. Também será possível consultar a posição na
fila, cancelar senhas e visualizar as filas existentes.
O sistema irá simular o funcionamento real de atendimento em bancos, permitindo
atendimento presencial e (online)??.
Para a implementação será utilizada a estrutura de dados Fila (Queue), seguindo a lógica
FIFO (First In, First Out), onde o primeiro cliente a entrar na fila será o primeiro a ser
atendido.
