CREATE TABLE public.breed (
	id uuid NOT NULL,
	integration_id varchar(50) NOT NULL,
	"name" varchar NOT NULL,
	origin_id uuid NOT NULL,
	created_at date NOT NULL,
	update_at date NOT NULL,
	CONSTRAINT breed_pk PRIMARY KEY (id),
	CONSTRAINT breed_integration_id_un UNIQUE (integration_id),
	CONSTRAINT breed_origin_fk FOREIGN KEY (origin_id) REFERENCES public.origin(id)
);