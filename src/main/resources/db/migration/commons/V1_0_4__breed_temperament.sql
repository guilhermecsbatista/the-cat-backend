CREATE TABLE public.breed_temperament (
	id uuid NOT NULL,
	breed_id uuid NOT NULL,
	temperament_id uuid NOT NULL,
	created_at date NOT NULL,
	update_at date NOT NULL,
	CONSTRAINT breed_temperament_pk PRIMARY KEY (id),
	CONSTRAINT breed_temperament_un UNIQUE (breed_id,temperament_id),
	CONSTRAINT breed_temperament_breed_fk FOREIGN KEY (breed_id) REFERENCES public.breed(id),
	CONSTRAINT breed_temperament_temperament_fk FOREIGN KEY (temperament_id) REFERENCES public.temperament(id)
);