You can find source codes in src folder

####################################################################################
#																				                                           #
#					Wuggy Turkish Plugin Installation Guide						                       #
#																				                                           #
####################################################################################

The Turkish plugin is written for Wuggy version 0.1.7 It should also work well with
the newest versions but if you encounter an error please install 0.1.7 version. 
After Wuggy installation put the files in the respective folders (e.g., Turkish plugin
file in data folder into Wuggy's data folder.

Then add following line to the Wuggy/plugins/__init__.py file

import subsyllabic_turkish

You also need to install following python libraries since Wuggy depends on them.

python-Levenshtein-0.10.1
wxpython

Or you can download the binary and run it directly :)

####################################################################################
#																				                                           #
#						Kelimetrik Installation Guide							                             #
#																				                                           #
####################################################################################

1. Download Eclipse from www.eclipse.org
2. Import KelimetriK project into your workspace
3. Install SWT jar file (the one that suits your platform) from http://www.eclipse.org/swt/
4. Add installed SWT jar file as an external jar file to the KelimetriK project settings
5. Run src/ui/MainWindow.java as a java program
