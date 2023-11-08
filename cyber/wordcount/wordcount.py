""" This program has been adapted for use by GVAHIM
       - the main revisions regard pep8 compliance and use of constants
Copyright 2010 Google Inc.
Licensed under the Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0

Google's Python Class
http://code.google.com/edu/languages/google-python-class/

Wordcount exercise
Google's Python class

The main() below is already defined and complete. It calls print_words()
and print_top() functions which you write.

1. For the --count flag, implement a print_words(filename) function that counts
how often each word appears in the text and prints:
word1 count1
word2 count2
...

Print the above list in order sorted by word (python will sort punctuation to
come before letters -- that's fine). Store all the words as lowercase,
so 'The' and 'the' count as the same word.

2. For the --topcount flag, implement a print_top(filename) which is similar
to print_words() but which prints just the top 20 most common words sorted
so the most common word is first, then the next most common, and so on.

Use str.split() (no arguments) to split on all whitespace.

Workflow: don't build the whole program at once. Get it to an intermediate
milestone and print your data structure and sys.exit(0).
When that's working, try for the next milestone.

Optional: define a helper function to avoid code duplication inside
print_words() and print_top().

"""
import string
import sys

REQUIRED_NUM_OF_ARGS = 3
ARG_OPTION = 1
ARG_FILE_NAME = 2

TOP_CUTOFF = 20  # in the print_top func, print this many entries

# +++your code here+++
# Define print_words(filename) and print_top(filename) functions.
# You could write a helper utility function that reads a file
# and builds and returns a word/count dict for it.
# Then print_words() and print_top() can just call the utility function.


def get_words(text):
    """
    formats the given text to be:
    a. lowercase
    b. without punctuation (excluding "'")
    :param text: a string of text
    :return: formatted text
    """
    words_with_apostrophe = text.lower().translate(str.maketrans('', '', string.punctuation.replace("'", ''))).split()
    out = []
    for word in words_with_apostrophe:
        if word[0] == "'":
            word = word[1:]
        if word[-1] == "'":
            word = word[:-1]
        out.append(word)

    return out
    # return words_with_apostrophe


def get_word_counts(filename):
    """
    creates a dict with words as keys and the number of times they appear in the file as the value
    :param filename: name of the file
    :return: dict representing word counts
    """
    word_counts = {}
    with open(filename, 'r') as file:
        contents = file.read()
    words = get_words(contents)
    for word in words:
        if word in word_counts.keys():
            word_counts[word] += 1
        else:
            word_counts[word] = 1
    return word_counts


def sort_word_counts(word_counts):
    """
    :param word_counts: a dict with word-count pairs
    :return: a sorted list with the pairs as tuples
    """
    return sorted(word_counts.items(), key=lambda item: item[1], reverse=True)


def print_words(filename):
    """
    print all words and the number of times they appear in the following format:
    <count> <word>
    :param filename: name of the file to be read
    :return: None
    """
    word_counts = sort_word_counts(get_word_counts(filename))
    for word, count in word_counts:
        print(f'{count} {word}')


def print_top(filename):
    """
    print the 20 most common words and the number of times they appear in the following format:
    <count> <word>
    :param filename: name of the file to be read
    :return: None
    """
    word_counts = sort_word_counts(get_word_counts(filename))[:TOP_CUTOFF]
    for word, count in word_counts:
        print(f'{count} {word}')


###

# This basic command line argument parsing code is provided and
# calls the print_words() and print_top() functions which you must define.
def main():
    if len(sys.argv) != REQUIRED_NUM_OF_ARGS:
        print('usage: ./wordcount.py {--count | --topcount} file')
        sys.exit(1)

    option = sys.argv[ARG_OPTION]
    filename = sys.argv[ARG_FILE_NAME]
    if option == '--count':
        print_words(filename)
    elif option == '--topcount':
        print_top(filename)
    else:
        print('unknown option: ' + option)
        sys.exit(1)


if __name__ == '__main__':
    main()
