CREATE TABLE public.temperament (
	id uuid NOT NULL,
	integration_id varchar(50) NOT NULL,
	description varchar NOT NULL,
	created_at date NOT NULL,
	update_at date NOT NULL,
	CONSTRAINT temperament_pk PRIMARY KEY (id),
	CONSTRAINT temperament_integration_id_un UNIQUE (integration_id)
);