CREATE TABLE public.image (
	id uuid NOT NULL,
	integration_id varchar(50) NOT NULL,
	url varchar NOT NULL,
	created_at date NOT NULL,
	update_at date NOT NULL,
	CONSTRAINT image_pk PRIMARY KEY (id),
	CONSTRAINT image_integration_id_un UNIQUE (integration_id)
);