import http from 'k6/http';
import { check } from 'k6';

const baseUrl = __ENV.BASE_URL || 'http://localhost:8080/v1';
const iterations = Number(__ENV.ITERATIONS || 100);
const vus = Number(__ENV.VUS || Math.min(20, iterations));

export const options = {
  scenarios: {
    relationship_reads: {
      executor: 'shared-iterations',
      vus,
      iterations,
      maxDuration: __ENV.MAX_DURATION || '2m',
    },
  },
  thresholds: {
    http_req_failed: ['rate<0.01'],
    http_req_duration: ['p(95)<800'],
  },
};

export function setup() {
  const suffix = `${Date.now()}`;
  const customerId = `CUS-K6-${suffix}`;
  const vehicleId = `VEH-K6-${suffix}`;
  const contractId = `CON-K6-${suffix}`;
  const headers = { 'Content-Type': 'application/json' };

  let response = http.post(`${baseUrl}/customers`, JSON.stringify({ id: customerId, firstName: 'Load', lastName: 'Test', postalCode: '75000', email: `load-${suffix}@example.test` }), { headers });
  check(response, { 'customer created': (r) => r.status === 201 });

  response = http.post(`${baseUrl}/vehicles`, JSON.stringify({ id: vehicleId, customerId, brand: 'Brand A', model: 'Load Model', registrationDate: '2024-01-01', mileage: 105000, initialValue: 40000 }), { headers });
  check(response, { 'vehicle created': (r) => r.status === 201 });

  response = http.post(`${baseUrl}/contracts`, JSON.stringify({ id: contractId, customerId, vehicleId, type: 'LEASE', startDate: '2024-01-01', endDate: '2026-12-15', status: 'ACTIVE', monthlyPayment: 500 }), { headers });
  check(response, { 'contract created': (r) => r.status === 201 });

  return { customerId };
}

export default function (data) {
  const response = http.get(`${baseUrl}/customers/${data.customerId}/relationship?asOf=2026-09-15`, { headers: { 'X-Correlation-Id': `k6-${__VU}-${__ITER}` } });
  check(response, {
    'relationship 200': (r) => r.status === 200,
    'contains customer': (r) => r.body.includes(data.customerId),
  });
}
