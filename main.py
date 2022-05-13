import hashlib

# m = hashlib.sha256()

print(hashlib.sha256(bytes("hello world", 'utf-8')).hexdigest())

print(hashlib.sha256(bytes('2022-05-13T18:38:00.506Z,60.1934,-141.314,4.7,1,ml,,,,0.89,ak,ak022649f3uf,2022-05-13T18:42:37.921Z,"114 km NW of Yakutat, Alaska",earthquake,,1.2,,,automatic,ak,ak', 'utf-8')).hexdigest())

print(hashlib.sha256(bytes('2022-05-13T18:38:00.506Z,60.1934,-141.314,4.7,1,ml,,,,0.89,ak,ak022649f3uf,2022-05-13T18:42:37.921Z,"114 km NW of Yakutat, Alaska",earthquake,,1.2,,,automatic,ak,ak', 'utf-8')).hexdigest())

print(hashlib.sha256(bytes('2022-05-13T18:38:00.507Z,60.1934,-141.314,4.7,1,ml,,,,0.89,ak,ak022649f3uf,2022-05-13T18:42:37.921Z,"114 km NW of Yakutat, Alaska",earthquake,,1.2,,,automatic,ak,ak', 'utf-8')).hexdigest())

## First Step: read in exisiting hashes as a list

from csv import reader, writer

def get_existing_hashes():
    existing_hashes = []

    with open('hashes', newline='\n') as csvfile:
        the_reader = reader(csvfile)
        for row in the_reader:
            existing_hashes.append(row[0])
    return existing_hashes

def save_hash(the_hash):
    print(the_hash)
    with open('hashes', 'a') as write_csv_file:
        the_writer = writer(write_csv_file)
        the_writer.writerow([the_hash])

if __name__ == "__main__":
    existing_hashes = get_existing_hashes()
    example_hashes = []

    example_hashes.append(hashlib.sha256(bytes('2022-05-13T18:38:00.506Z,60.1934,-141.314,4.7,1,ml,,,,0.89,ak,ak022649f3uf,2022-05-13T18:42:37.921Z,"114 km NW of Yakutat, Alaska",earthquake,,1.2,,,automatic,ak,ak', 'utf-8')).hexdigest())

    example_hashes.append(hashlib.sha256(bytes('2022-05-13T18:38:00.507Z,60.1934,-141.314,4.7,1,ml,,,,0.89,ak,ak022649f3uf,2022-05-13T18:42:37.921Z,"114 km NW of Yakutat, Alaska",earthquake,,1.2,,,automatic,ak,ak', 'utf-8')).hexdigest())

    for hash in example_hashes:
        if hash not in example_hashes:
            # save_hash(hash)
            save_hash(hash)
            # write the full line to the other CSV file

print(get_existing_hashes())


## open CSV file of geo data

## hash each line and compare against existing  hashes --> if doesn't exist write in two locations

## Second Step: open the CSV for each do the lower steps

# read the file (which happens to be a CSV)

# for each line

#   [X] hash
#   compare hash against file contents
#       flatter (one read, then one read)
#       
#   if no match add to file (hash)
#   and add record to unique-geological-events.csv

