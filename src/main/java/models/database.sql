create table if not exists client
(
	id serial not null
		constraint client_pkey
			primary key,
	nom varchar not null,
	prenom varchar
);

alter table client owner to postgres;

create table if not exists compte_type
(
	id serial not null
		constraint compte_type_pkey
			primary key,
	name varchar not null
);

alter table compte_type owner to postgres;

create table if not exists compte
(
	id serial not null
		constraint compte_pkey
			primary key,
	client_id integer not null
		constraint fk_client
			references client,
	no_compte bigint not null,
	type integer not null
		constraint fk_type_compte
			references compte_type,
	solde real default 0.0 not null
);

alter table compte owner to postgres;

