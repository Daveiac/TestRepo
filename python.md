# Python help
## Hvordan angripe et kodeproblem:
* Hva spør oppgaven etter? Skal du lage en funksjon, et program?
* Hvordan får du input? Skal det bare mates inn som et argument til en funksjon? Skal det leses inn fra brukeren? Skal det leses fra fil?
* Hva skal skal returneres? Skal du returnere en liste, tall, streg etc.? Skal du printe noe til skjermen? Skal funksjonen din returnere noe?

Hvis du ikke vet helt hvordan du skal løse noe så kan du jukse litt ved å lage en hjelpefunksjon, eksempel denne oppgaven fra 2016 kont:
>Lag funksjonen load_bin som har en inn-parameter filename, som er navnet på fila som skal
lastes inn. Funksjonen skal lese inn alt innholdet i fila og returnere innholdet som en tekststreng uten
linjeskift eller mellomrom. Fila som det leses fra er en tekstfil bestående av binære tall (0 og 1). Hvis
fila ikke eksisterer eller ikke kan åpnes, skal funksjonen returnere en tom streng samt skrive ut
følgende feilmelding til skjerm: ”Error: Could not open file <filename>”, der <filename> er navnet
på fila.

Kanskje du ikke husker hvordan du skal fjerne linjeskift/mellomrom, da kan du bare lage en hjelpefunksjon som du antar at virker, som dette:
```python
def load_bin(filename):
    f = open(filename, 'r')
    data = f.read()
    data = strip_whitespace(data)
    return data

# This function removes all spaces and newline characters from the given string.
def strip_whitespace(string):
    # I don't know how to do this :(
```
Du kan fortsatt få poeng fordi du har forstått hva du skal gjøre, men du husker bare ikke hvordan du løser en spesefikk ting.


