package br.com.apinotesimplifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.apinotesimplifier.dto.SellItemDTO;
import br.com.apinotesimplifier.interfaces.PaymentMethodService;
import br.com.apinotesimplifier.interfaces.ProductService;
import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.interfaces.SellItemService;
import br.com.apinotesimplifier.interfaces.ServiceProvidedService;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.Address;
import br.com.apinotesimplifier.models.PaymentMethod;
import br.com.apinotesimplifier.models.PersonalData;
import br.com.apinotesimplifier.models.Product;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SalePayment;
import br.com.apinotesimplifier.models.SellItem;
import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;

@SpringBootApplication
public class NoteSimplifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteSimplifierApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(
			UserService userService,
			RoleService roleService,
			ProductService productService,
			SaleService saleService,
			PaymentMethodService payMethService,
			SellItemService sellItemService,
			ServiceProvidedService serviceProvidedService) {
		return args -> {
			roleService.saveRole(new Role(null, "ROLE_USER"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SELLER"));
			roleService.saveRole(new Role(null, "ROLE_PROFESSIONAL"));

			User normal_user = new User();
			normal_user.setUsername("normal_user");
			normal_user.setPassword("password");
			normal_user.setProfession("Usuário Normal");
			normal_user.setAccountStatus("active");
			Address address_normal_user = new Address();
			address_normal_user.setDistrict("Plan. Dir. Sul");
			address_normal_user.setRoad("NS2");
			address_normal_user.setNumber("03");
			address_normal_user.setComplement("Condomínio Palmeira Dourada");
			address_normal_user.setCity("Palmas");
			address_normal_user.setUf("TO");
			PersonalData data_normal_user = new PersonalData();
			data_normal_user.setName("Usuário normal");
			data_normal_user.setEmail("normal_user@mail.com");
			data_normal_user.setContact("63985001122");
			data_normal_user.setAddresses(Arrays.asList(address_normal_user));
			normal_user.setIdPersonalData(data_normal_user);
			userService.save(normal_user);

			User user_admin = new User();
			user_admin.setUsername("admin_user");
			user_admin.setPassword("password");
			user_admin.setProfession("Admin");
			user_admin.setAccountStatus("active");
			Address address_user_admin = new Address();
			address_user_admin.setDistrict("Plan. Dir. Sul");
			address_user_admin.setRoad("NS2");
			address_user_admin.setNumber("03");
			address_user_admin.setComplement("Condomínio Palmeira Monarca");
			address_user_admin.setCity("Palmas");
			address_user_admin.setUf("TO");
			PersonalData data_user_admin = new PersonalData();
			data_user_admin.setName("Usuário Admin");
			data_user_admin.setEmail("admin_user@mail.com");
			data_user_admin.setContact("63985002233");
			data_user_admin.setAddresses(Arrays.asList(address_user_admin));
			user_admin.setIdPersonalData(data_user_admin);
			userService.save(user_admin);

			User user_super_admin = new User();
			user_super_admin.setUsername("super_admin_user");
			user_super_admin.setPassword("password");
			user_super_admin.setProfession("Super admin");
			user_super_admin.setAccountStatus("active");
			Address address_user_super_admin = new Address();
			address_user_super_admin.setDistrict("Plan. Dir. Sul");
			address_user_super_admin.setRoad("NS2");
			address_user_super_admin.setNumber("03");
			address_user_super_admin.setComplement("Condomínio Porto Seguro");
			address_user_super_admin.setCity("Brasília");
			address_user_super_admin.setUf("DF");
			PersonalData data_user_super_admin = new PersonalData();
			data_user_super_admin.setName("Usuário Super Admin");
			data_user_super_admin.setEmail("super_admin_user@mail.com");
			data_user_super_admin.setContact("63985003344");
			data_user_super_admin.setAddresses(Arrays.asList(address_user_super_admin));
			user_super_admin.setIdPersonalData(data_user_super_admin);
			userService.save(user_super_admin);

			User user_seller = new User();
			user_seller.setUsername("profissional_user_seller");
			user_seller.setPassword("password");
			user_seller.setProfession("Seller");
			user_seller.setAccountStatus("active");
			Address address_user_seller = new Address();
			address_user_seller.setDistrict("Plan. Dir. Sul");
			address_user_seller.setRoad("NS2");
			address_user_seller.setNumber("03");
			address_user_seller.setComplement("Condomínio Águas do Vale");
			address_user_seller.setCity("São Paulo");
			address_user_seller.setUf("SP");
			PersonalData data_user_seller = new PersonalData();
			data_user_seller.setName("Usuário Profissional");
			data_user_seller.setEmail("seller_profissional_user@mail.com");
			data_user_seller.setContact("63985004455");
			data_user_seller.setAddresses(Arrays.asList(address_user_seller));
			user_seller.setIdPersonalData(data_user_seller);
			userService.save(user_seller);

			User professional_user_provider = new User();
			professional_user_provider.setUsername("professional_user_provider");
			professional_user_provider.setPassword("password");
			professional_user_provider.setProfession("Provider");
			professional_user_provider.setAccountStatus("active");
			Address address_professional_user_provider = new Address();
			address_professional_user_provider.setDistrict("Plan. Dir. Sul");
			address_professional_user_provider.setRoad("MS2");
			address_professional_user_provider.setNumber("03");
			address_professional_user_provider.setComplement("Condomínio Vale Verde");
			address_professional_user_provider.setCity("Paraíso");
			address_professional_user_provider.setUf("TO");
			PersonalData data_professional_provider = new PersonalData();
			data_professional_provider.setName("Usuário Profissional Prestador de Serviços");
			data_professional_provider.setEmail("professional_user_provider@mail.com");
			data_professional_provider.setContact("63985005566");
			data_professional_provider.setAddresses(Arrays.asList(address_professional_user_provider));
			professional_user_provider.setIdPersonalData(data_professional_provider);
			userService.save(professional_user_provider);

			userService.addRoleToUser("normal_user", "ROLE_USER");
			userService.addRoleToUser("admin_user", "ROLE_ADMIN");
			userService.addRoleToUser("super_admin_user", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("profissional_user_seller", "ROLE_SELLER");
			userService.addRoleToUser("professional_user_provider", "ROLE_PROFESSIONAL");

			PaymentMethod payMeth_money = payMethService.save(new PaymentMethod(null, "MONEY", "DINHEIRO AVISTA"));
			PaymentMethod payMeth_cred = payMethService.save(new PaymentMethod(null, "CREDIT_SPOTS", "CREDITO AVISTA"));
			// new PaymentMethod(null "CREDIT_IN_INSTALLMENTS", "CREDITO PARCELADO");

			Product product1 = productService
					.save(new Product(null, "product1", "Produto 1", "UN", "19922811", "21941", BigDecimal.valueOf(20), 1));
			Product product2 = productService
					.save(new Product(null, "produto2", "Produto 2", "CX", "19922866", "21956", BigDecimal.valueOf(50), 5));
			Product product3 = productService
					.save(new Product(null, "produto3", "Produto 3", "UN", "19922855", "21976", BigDecimal.valueOf(70), 1));
			Product product4 = productService
					.save(new Product(null, "produto4", "Produto 4", "CX", "19922833", "21984", BigDecimal.valueOf(20), 4));

			SalePayment salePayment = new SalePayment();
			salePayment.setPaymentMethods(Arrays.asList(payMeth_money, payMeth_cred));
			salePayment.setInstallmentForm(1);
			salePayment.setTotal(BigDecimal.valueOf(1000));
			salePayment.setPayday(LocalDate.now());
			salePayment.setIdSale(null);
			salePayment.setSituation("PROCESSING");
			salePayment.setDate(LocalDate.now());

			Sale sale = new Sale();
			sale.setIdClient(new User(Long.valueOf(1)));
			sale.setIdSeller(new User(Long.valueOf(4)));
			sale.setIdSalePayment(salePayment);
			sale.setDateOfSale(LocalDate.now());
			sale.setVlTotal(BigDecimal.valueOf(1000));
			Sale saleCreated = saleService.save(sale);

			List<SellItem> sellItems = new ArrayList<>();
			SellItem sellItem1 = sellItemService
					.saveWithIds(new SellItemDTO(null, product1.getId(), product1.getName(), 2, sale.getId()));
			SellItem sellItem2 = sellItemService
					.saveWithIds(new SellItemDTO(null, product2.getId(), product1.getName(), 2, sale.getId()));
			SellItem sellItem3 = sellItemService
					.saveWithIds(new SellItemDTO(null, product3.getId(), product1.getName(), 2, sale.getId()));
			SellItem sellItem4 = sellItemService
					.saveWithIds(new SellItemDTO(null, product4.getId(), product1.getName(), 2, sale.getId()));
			sellItems.add(sellItem1);
			sellItems.add(sellItem2);
			sellItems.add(sellItem3);
			sellItems.add(sellItem4);
			saleCreated.setSellItems(sellItems);

			serviceProvidedService.saveServiceprovidedWithIds(new ServiceProvided(null, null, null, "",
					null, LocalDate.now()), Long.valueOf(5), Long.valueOf(1));
		};
	}
}
