package com.jhindin.phonotone.instruments;

public class Family {
    public String name;
    public Instrument instruments[];

    public Family(String name, Instrument... instruments) {
        this.name = name;
        this.instruments = instruments;
    }

    public static Family generalMidiFamilies[] = new Family[]{
            new Family("Piano",
                    new Instrument("Acoustic Grand Piano", 0),
                    new Instrument("Bright Acoustic Piano", 1),
                    new Instrument("Electric Grand Piano", 2),
                    new Instrument("Honky Tonk Piano", 3),
                    new Instrument("Electric Piano 0", 4),
                    new Instrument("Electric Piano 1", 5),
                    new Instrument("Harpsichord", 6),
                    new Instrument("Clavi", 7)),

            new Family("Chromatic Percussion",
                    new Instrument("Celesta", 8),
                    new Instrument("Glockenspiel", 9),
                    new Instrument("Music Box", 10),
                    new Instrument("Vibraphone", 11),
                    new Instrument("Marimba", 12),
                    new Instrument("Xylophone", 13),
                    new Instrument("Tubular Bells", 14),
                    new Instrument("Dulcimer", 15)),

            new Family("Organ",
                    new Instrument("Drawbar Organ", 16),
                    new Instrument("Percussive Organ", 17),
                    new Instrument("Rock Organ", 18),
                    new Instrument("Church Organ", 19),
                    new Instrument("Reed Organ", 20),
                    new Instrument("Accordion", 21),
                    new Instrument("Harmonica", 22),
                    new Instrument("Tango Accordion", 23)),

            new Family("Guitar",
                    new Instrument("Acoustic Guitar Nylon", 24),
                    new Instrument("Acoustic Guitar Steel", 25),
                    new Instrument("Electric Guitar Jazz", 26),
                    new Instrument("Electric Guitar Clean", 27),
                    new Instrument("Electric Guitar Muted", 28),
                    new Instrument("Overdriven Guitar", 29),
                    new Instrument("Distortion Guitar", 30),
                    new Instrument("Guitar Harmonics", 31)),

            new Family("Bass",
                    new Instrument("Acoustic Bass", 32),
                    new Instrument("Electric Bass Finger", 33),
                    new Instrument("Electric Bass Pick", 34),
                    new Instrument("Fretless Bass", 35),
                    new Instrument("Slap Bass 0", 36),
                    new Instrument("Slap Bass 1", 37),
                    new Instrument("Synth Bass 0", 38),
                    new Instrument("Synth Bass 1", 39)),

            new Family("Strings",
                    new Instrument("Violin", 40),
                    new Instrument("Viola", 41),
                    new Instrument("Cello", 42),
                    new Instrument("Contrabass", 43),
                    new Instrument("Tremolo Strings", 44),
                    new Instrument("Pizzicato Strings", 45),
                    new Instrument("Orchestral Harp", 46),
                    new Instrument("Timpani", 47)),

            new Family("Ensemble",
                    new Instrument("String Ensemble 0", 48),
                    new Instrument("String Ensemble 1", 49),
                    new Instrument("Synthstrings 0", 50),
                    new Instrument("Synthstrings 1", 51),
                    new Instrument("Choir Aahs", 52),
                    new Instrument("Voice Oohs", 53),
                    new Instrument("Synth Voice", 54),
                    new Instrument("Orchestra Hit", 55)),

            new Family("Brass",
                    new Instrument("Trumpet", 56),
                    new Instrument("Trombone", 57),
                    new Instrument("Tuba", 58),
                    new Instrument("Muted Trumpet", 59),
                    new Instrument("French Horn", 60),
                    new Instrument("Brass Section", 61),
                    new Instrument("Synthbrass 0", 62),
                    new Instrument("Synthbrass 1", 63)),

            new Family("Reed",
                    new Instrument("Soprano", 64),
                    new Instrument("Alto Sax", 65),
                    new Instrument("Tenor Sax", 66),
                    new Instrument("Baritone Sax", 67),
                    new Instrument("Oboe", 68),
                    new Instrument("English Horn", 69),
                    new Instrument("Bassoon", 70),
                    new Instrument("Clarinet", 71)),

            new Family("Pipe",
                    new Instrument("Piccolo", 72),
                    new Instrument("Flute", 73),
                    new Instrument("Recorder", 74),
                    new Instrument("Pan Flute", 75),
                    new Instrument("Blown Bottle", 76),
                    new Instrument("Shakuhachi", 77),
                    new Instrument("Whistle", 78),
                    new Instrument("Ocarina", 79)),

            new Family("Synth Lead",
                    new Instrument("Lead 0 Square", 80),
                    new Instrument("Lead 1 Sawtooth", 81),
                    new Instrument("Lead 2 Calliope", 82),
                    new Instrument("Lead 3 Chiff", 83),
                    new Instrument("Lead 4 Charang", 84),
                    new Instrument("Lead 5 Voice", 85),
                    new Instrument("Lead 6 Fifths", 86),
                    new Instrument("Lead 7 Bass Lead", 87)),

            new Family("Synth Pad",
                    new Instrument("Pad 0 New Age", 88),
                    new Instrument("Pad 1 Warm", 89),
                    new Instrument("Pad 2 Polysynth", 90),
                    new Instrument("Pad 3 Choir", 91),
                    new Instrument("Pad 4 Bowed", 92),
                    new Instrument("Pad 5 Metallic", 93),
                    new Instrument("Pad 6 Halo", 94),
                    new Instrument("Pad 7 Sweep", 95)),

            new Family("Synth Effects",
                    new Instrument("Fx 0 Rain", 96),
                    new Instrument("Fx 1 Soundtrack", 97),
                    new Instrument("Fx 2 Crystal", 98),
                    new Instrument("Fx 3 Atmosphere", 99),
                    new Instrument("Fx 4 Brightness", 100),
                    new Instrument("Fx 5 Goblins", 101),
                    new Instrument("Fx 6 Echoes", 102),
                    new Instrument("Fx 7 Sci Fi", 103)),

            new Family("Ethnic",
                    new Instrument("Sitar", 104),
                    new Instrument("Banjo", 105),
                    new Instrument("Shamisen", 106),
                    new Instrument("Koto", 107),
                    new Instrument("Kalimba", 108),
                    new Instrument("Bag Pipe", 109),
                    new Instrument("Fiddle", 110),
                    new Instrument("Shanai", 111)),

            new Family("Percussive",
                    new Instrument("Tinkle Bell", 112),
                    new Instrument("Agogo", 113),
                    new Instrument("Steel Drums", 114),
                    new Instrument("Woodblock", 115),
                    new Instrument("Taiko Drum", 116),
                    new Instrument("Melodic Tom", 117),
                    new Instrument("Synth Drum", 118),
                    new Instrument("Reverse Cymbal", 119)),

            new Family("Sound Effects",
                    new Instrument("Guitar Fret Noise", 120),
                    new Instrument("Breath Noise", 121),
                    new Instrument("Seashore", 122),
                    new Instrument("Bird Tweet", 123),
                    new Instrument("Telephone Ring", 124),
                    new Instrument("Helicopter", 125),
                    new Instrument("Applause", 126),
                    new Instrument("Gunshot", 127))
    };
}


