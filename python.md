# Python help
## Hvordan angripe et kodeproblem:
### Grunnleggende info
Les oppgaven nøye, her får du masse hjelp om hvordan den skal løses. Prøv å bryt opp oppgaven i små deler, og let etter stikkord.
* Hva spør oppgaven etter? Skal du lage en funksjon, et program?
* Hvordan får du input? Skal det bare mates inn som et argument til en funksjon? Skal det leses inn fra brukeren? Skal det leses fra fil?
* Hvilke type datastrukturer får du som input? Får du inn en liste, dictionary, streng, filnavn etc., kanskje flere typer?
* Hva skal skal returneres? Skal du returnere en liste, tall, streg etc.? Skal du printe noe til skjermen? Skal du skrive til fil? Skal funksjonen din returnere noe?

Tenk over disse spørsmålene, og tenk også over hvilke funksjoner som gjelder for de forskjellige datastrukturene. Hvis du feks. vet at du får inn en liste, så kan det hende du må iterere over den ved å bruke en `for` løkke. Kanskje du skal ta inn input fra bruker osv. da vet du at du må bruke `input()`.

Når du skal skrive en funksjon som skal returnere noe så er det ofte lurt å opprette den variablen i starten av funksjonen. Feks. hvis du skal bygge en liste i funksjonen så skriver du `l = []`, for streng `s = ""`, for dictionary `d = {}` etc.

Her er et eksempel på en oppgave fra Kont 2016:
>Lag funksjonen load_bin som har en inn-parameter filename, som er navnet på fila som skal
lastes inn. Funksjonen skal lese inn alt innholdet i fila og returnere innholdet som en tekststreng uten
linjeskift eller mellomrom. Fila som det leses fra er en tekstfil bestående av binære tall (0 og 1). Hvis
fila ikke eksisterer eller ikke kan åpnes, skal funksjonen returnere en tom streng samt skrive ut
følgende feilmelding til skjerm: ”Error: Could not open file <filename>”, der <filename> er navnet
på fila.

Hvis vi skal prøve å bryte ned denne oppgaven:  
>"Lag funksjonen load_bin", "inn-parameter filename"

Ok her skjønner vi at vi må bruke `def load_bin(filename)`.  
> "Funksjonen skal lese inn alt innholdet i fila"

Vi skal lese fra fil, da må vi bruke `f.open(filename, "r")` og `f.read()`.  
>"returnere innholdet som en tekststreng"

Vi må i allefall skrive `return` på slutten av funksjonen

### Hjelpefunksjon
Hvis du ikke vet helt hvordan du skal løse noe så kan du jukse litt ved å lage en hjelpefunksjon, la oss bruke eksempelet fra i stad. Kanskje du ikke husker hvordan du skal fjerne linjeskift/mellomrom, da kan du bare lage en hjelpefunksjon som du antar at virker, som dette:
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
Eksempler på situasjoner hvor du kanskje vil bruke en hjelpefunksjon fordi du ikke husker hvordan du skal løse noe  
Du får beskjed om å:

* Sortere en liste på en spesiell måte
* Lese inn fra fil på en spesiell måte
* Andre ting du ikke husker helt hvordan du skal gjøre, eller er usikker på.

Hvis du har en idé om hvordan hjelpefunksjonen skal se ut så kan du også prøve å fylle den ut, eksempel fra i stad:
```python
# This function removes all spaces and newline characters from the given string.
def strip_whitespace(string):
    # This doesn't do exactly what I want, but I know i'm close here...
    return string.strip()
```
