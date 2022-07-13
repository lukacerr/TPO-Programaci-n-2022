import random

# Modificar esta lista con las nomenclaturas correspondientes
lista = ['P+3', 'P1', 'P3', 'O', 'P']
# Modificar esta valor con la cantidad de valores a apreciar en la cadena
rango = 40


str = ''
for i in range(rango): str += ', ' + random.choice(lista)
print(str[2:])
