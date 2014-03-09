# Subsyllabic Turkish
public_name='Subsyllabic Turkish'
default_data='turkish_lexicon.txt'
default_neighbor_lexicon='turkish_lexicon.txt'
default_word_lexicon='turkish_lexicon.txt'
default_lookup_lexicon='turkish_lexicon.txt'
from subsyllabic_common import *
import orth.tr as language
def transform(input_sequence, frequency=1):
    return pre_transform(input_sequence, frequency=frequency, language=language)
