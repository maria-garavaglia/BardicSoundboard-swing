# Bardic Soundboard

POC: [Maria Garavaglia](mailto:maria.renie.garavaglia@gmail.com)

This is a customizable soundboard originally meant to accompany Bard spells in a D&D campaign. It supports saving and loading of multiple characters, as well as multiple spells per character. There are two versions of this application: this one uses Swing, while the other version uses JavaFX.

## Building/Running

Requires Java 17+ and Maven to run. Building and running happens in one step, simply run `mvn clean spring-boot:run` to start.

Saving and loading of characters is done through the File menu. There is a sample character (Hrothgar Hammerfall) in `Characters/`, as well as some sample audio files in `Audio/`.

Double-click a spell to play it, or use the Play/Stop and volume controls at the bottom. Playing a new spell should stop any spells currently playing.

## Programming Notes

This is the newer of the two versions, written in 2025 utilizing the latest knowledge from my time working on in-house development tools for the Navy. I tried including some of the more useful things I learned while modernizing the devtools suite in this project:

* Playing with JavaFX and FXML in the original soundboard heavily influenced the view/controller UI design. Separating the layout from the logic is huge for maintenance when dealing with big applications and complex forms.
* Spring Boot was helpful in bootstrapping and wiring things together, though in a small project like this it feels a bit overkill.
* I know Maven has been around a long time, but after building and managing dependencies manually at work for far too long, I'll never go without it now.
* Logging libraries are also standard now, though for the longest time the devtools suite just used `System.out.println` because adding the dependency wasn't worth it. Once I started using slf4j I really liked using `{}` to insert variables in log messages, so I wrote a wrapper class to format any `String` in the same way. It's included separately in both soundboards, though my next project will likely be a common utilities library that I'll use for everything going forward.
* The devtools suite originally used an old version of json-simple for reading/saving configs, so I tried a more updated version of that for the JavaFX soundboard. I've since tried Jackson (through Spring Boot) and it's so much easier to manage.
* I'm still working on incorporating test-driven design into my workflow, especially in UI-heavy applications where everything feels so interconnected and focused on user actions.

I have some TODOs sprinkled around the project at the moment, I think I'd tackle those first (particularly keyboard shortcuts) in future updates.

