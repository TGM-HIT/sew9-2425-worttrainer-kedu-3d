# sew9-2425-worttrainer-kedu-3d
sew9-2425-worttrainer-kedu-3d created by GitHub Classroom

Ein Rechtschreibtrainer, bei dem Kinder zu einem angezeigten Bild das passende Wort eingeben und eine Rückmeldung erhalten. 
Das Programm speichert eine Statistik über richtige und falsche Versuche und ermöglicht es, den Fortschritt zu persistieren.

## Dependencies

- gradle
- JDK21

## Funktionen

### Start
Standardmäßig gibt es drei Bilddateien, welche angezeigt werden können.
Bei Start wird ein zufälliges Bild ausgewählt und anschließend angezeigt, welches erraten werden kann.

### GUI

![image](https://github.com/user-attachments/assets/e1d4e1b6-0dfd-4e5b-bafb-897701b79f2c)


#### Textfeld 
In dem Textfeld kann das Wort, welches auf dem Bilddargestellt wird, angegeben werden(Groß- und Kleinschreibung ist dabei irrelevant). Anschließend kann die Richtigkeit des Wortes durch einen enter-Klick überprüft werden. Anschließend kann man links unten sehen ob die Eingabe richtig war oder nicht. Bei richtiger Eingabe erhöhen sich beide Zähler und bei falscher Eingabe erhöht sich nur der Zähler "Anzahl Woerter"

#### Fortschritt speichern Button
Bei klick auf diesen Button wird der Fortschritt (Aktuelles Bild, Richtige Woerter, Falsche Woerter) gespeichert.

#### Forschritt laden Button
Bei klick auf diesen Button wird der Fortschritt (Aktuelles Bild, Richtige Woerter, Falsche Woerter) geladen. 

#### Wortliste ausgeben
Hier kann die gesamte Liste an Wörtern inkl. der dazugehörigen Url angezeigt werden.

#### Hilfe
Dies ist eine kleine Beschreibung des Spiels

#### Zuruecksetzen
Dieser Button setzt den aktuellen Stand (Richtige Woerter, Anzahl Woerter) zurück.

#### Wort hinzufuegen
Bei Klick erscheinen zwei Fenster:
1. Als erstes muss das Wort eingegeben werden
2. Als zweites muss der dazugehörige URL angegeben werden
   - Hierbei muss beachtet werden, dass die URL mit http://a.a oder https://a.a beginnt und mit jpg|jpeg|png|webp|avif|gif endet.
