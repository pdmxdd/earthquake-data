import hashlib

from os import environ

from csv import reader, writer, DictWriter

HOME_DIR = environ["HOME"]
DATA_DIR = f"{HOME_DIR}/.earthquake-data/"

def get_existing_hashes():
    existing_hashes = []

    with open(f'{DATA_DIR}hashes', newline='\n') as csvfile:
        the_reader = reader(csvfile)
        for row in the_reader:
            existing_hashes.append(row[0])
    return existing_hashes

def save_hash(the_hash):
    with open(f'{DATA_DIR}hashes', 'a') as write_csv_file:
        the_writer = writer(write_csv_file)
        the_writer.writerow([the_hash])

def read_requested_file():
    data = []
    with open(f"{DATA_DIR}eq-data.csv", 'r') as csvfile:
        the_reader = reader(csvfile)
        for row in the_reader:
            data.append(",".join(row))
    key_data = data.pop(0)
    return key_data, data

def keys_file_data_to_dict(str_keys, events_as_strs):
    the_dict = {}
    keys_list = str_keys.split(",")
    for event_str in events_as_strs:
        event_as_list = event_str.split(",")
        the_dict[hash_full_row(event_str)] = {
            keys_list[i]: event_as_list[i] for i in range(len(keys_list))
        }
    return the_dict

def hash_full_row(full_row):
    return hashlib.sha256(bytes(full_row, 'utf-8')).hexdigest()

if __name__ == "__main__":
    existing_hashes = get_existing_hashes()

    file_keys, events_as_str_in_list = read_requested_file()

    event_by_hash_dict = keys_file_data_to_dict(file_keys, events_as_str_in_list)

    for hash, data_dict in event_by_hash_dict.items():
        if hash not in existing_hashes:
            # save_hash(hash)
            save_hash(hash)
            # write the full line to the other CSV file
            with open(f'{DATA_DIR}exactly-once-eq-data.csv', 'a') as csvfile:
                dict_writer = DictWriter(csvfile, fieldnames=data_dict.keys())
                dict_writer.writerow(data_dict)